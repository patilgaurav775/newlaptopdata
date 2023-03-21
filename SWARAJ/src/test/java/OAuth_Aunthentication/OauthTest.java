package OAuth_Aunthentication;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class OauthTest {

	public static void main(String[] args) {
		
		
		System.setProperty("webdriver.chrome.driver", ".\\PRACTICE\\src\\test\\resources\\RESOURSES\\chromedriver1.exe");
		WebDriver driver=new ChromeDriver();
		
		
		
		
		
		
		
		String Access_Token_Response=
		 given()
		.queryParams("code","" )
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when()
		.log().all()
		.post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
		
		JsonPath js=new JsonPath("Access_Token_Response");
		String ACCESS_TOKEN=js.getString("access_token");
		
		
		
		
		
		String respo=
		 given()
		.queryParam("access_token", "ACCESS_TOKEN")
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		
		System.out.println(respo);

	}

}
