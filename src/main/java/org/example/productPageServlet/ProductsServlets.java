package org.example.productPageServlet;

import org.example.product.Product;
import org.example.productsManage.ProductRepoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/ProductsPage")
public class ProductsServlets extends HttpServlet {
    ProductRepoImpl productRepo = new ProductRepoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Products Page</title></head>");
        out.println("<body>");
        out.println("<h1>Enter Details</h1>");
        out.println("<form method='post' action='ProductsPage'>");  // Updated action URL
        out.println("Product Name: <input type='text' name='field1'required><br><br>");
        out.println("Product Price: <input type='number' name='field2'><br><br>");
        out.println("<button type='submit' name='action' value='AddBTN'>Add Product</button>");
        out.println("<button type='submit' name='action' value='DeleteBTN'>Remove Product</button>");
        out.println("<button type='submit' name='action' value='UpdateBTN'>Update Product</button>");
        out.println("<button type='submit' name='action' value='GetBTN'>Find</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process the form submission
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String field1 = request.getParameter("field1");
        String field2Str = request.getParameter("field2");
        float field2=0;
        if(!field2Str.isEmpty()){
             field2 = Float.parseFloat(request.getParameter("field2"));
        }
        String action = request.getParameter("action");

        out.println("<html>");
        out.println("<head><title>Input Servlet</title></head>");
        out.println("<body>");

        switch (action) {
            case "AddBTN": {
                 if (field2 == 0) {
                    out.println("<p>Price can't be zero when adding a product</p>");
                } else {
                     if(productRepo.addProduct(new Product(field1, field2))){
                         out.println("<p>Product Added </p>");
                     }
                     else{
                         out.println("<p>Product Already in stock you can update instead</p>");
                         Product existingProduct=productRepo.getProduct(field1).get();
                         out.println("<p>"+existingProduct+"</p>");
                     }
                }
                break;
            }
            case "DeleteBTN": {
                if(productRepo.deleteProduct(field1)){
                    out.println("<p> Product Deleted Successfully</P>");
                }
                else{
                    out.println("<p> There is no Product with that name \""+field1+"\" in the stock</p>");
                }
                break;
            }
            case "UpdateBTN":
                if (field2 == 0) {
                    out.println("<p>Price can't be zero when updating a product</p>");
                }
                else{
                    if(productRepo.updateProduct(new Product(field1,field2))){
                        out.println("<p> Product updated Successfully</P>");
                    }
                    else{
                        out.println("<p> There is no Product with that name \""+field1+"\" in the stock you can add instead</p>");
                    }
                }
                break;
            case "GetBTN":
                Optional<Product> product = productRepo.getProduct(field1);
                if(product.isPresent()){
                    out.println("<p> "+product.get()+" </p>");
                }
                else{
                    out.println("<p> Couldn't find \""+field1+"\" try adding it" );
                }

                break;
        }

        out.println("<br><a href='ProductsPage'>Back</a>");
        out.println("</body>");
        out.println("</html>");
    }


}
