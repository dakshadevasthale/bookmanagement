package controllers;

import beans.User;
import play.mvc.*;
import services.UserRegistration;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */


public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result userLogin() {
        return ok(views.html.login.render());
    }

    public Result register() {return ok (views.html.register.render());}

    public Result populateBooks(Http.Request request){

        var formData = request.body().asFormUrlEncoded();
        var studentName = formData.get("sName")[0];
        return ok();
    }



   public Result login(Http.Request request) {

        var formData = request.body().asFormUrlEncoded();
        var userName = formData.get("userName")[0];
        var password = formData.get("password")[0];

       /*
       System.out.println(userName);
       System.out.println(password);*/


        return ok(views.html.UserManagement.render());
    }

    public Result createUser(Http.Request request) throws ParseException, SQLException {
        var formData = request.body().asFormUrlEncoded();
        var firstName = formData.get("firstname")[0];
        var lastName = formData.get("lastname")[0];
        var secQues = formData.get("selectbasic")[0];
        var secQuesAns = formData.get("securityanswer")[0];
        var dateOfBirth = formData.get("dob")[0];
        var address = formData.get("address")[0];
        var email = formData.get("email")[0];
        var userName = formData.get("username")[0];
        var password = formData.get("password")[0];
        var cnfPassword = formData.get("cnfpassword")[0];
        var contactNumber = formData.get("contactnumber")[0];

        /*System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(secQues);
        System.out.println(secQuesAns);
        System.out.println(dateOfBirth);
        System.out.println(address);
        System.out.println(email);
        System.out.println(userName);
        System.out.println(password);
        System.out.println(cnfPassword);
        System.out.println(contactNumber);*/
        System.out.println(dateOfBirth);
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole("Student");
        newUser.setSecurityA(secQuesAns);
        newUser.setSecurityQ(secQues);
        newUser.setStatus(1);
        newUser.setUserId(userName);

        newUser.setAddress(address);
        newUser.setContactNo(contactNumber);
        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
        newUser.setDateOfBirth(dob);
        newUser.setfName(firstName);
        newUser.setlName(lastName);
        newUser.setUserId(userName);
        UserRegistration userReg = new UserRegistration();
        userReg.createUser(newUser);
        return ok();


    }

    public Result forgotPassword()
    {
        return  ok(views.html.ResetPwd.render());
    }
}
