import java.util.Scanner;
import java.util.ArrayList;
import java.net.URL;

public class webCrawler{
	
	public static void main(String[] args) {
		//Prompt user for URL
		System.out.print("Enter a URL..");
		String webAddy = new Scanner(System.in).next();
		System.out.print(webAddy);
		//invoke crawler method with URL user entered as parameter
		crawler(webAddy);
	}	
		//crawler method
		public static void crawler(String startUrl){
		// Make 2 ArrayLists to keep track of URL's to be added and Url's already traversed
			ArrayList<String> pendingURL = new ArrayList<>();
			ArrayList<String> finishedURL = new ArrayList<>();
		//add user entered url as starting point into pending URL ArrayList
			pendingURL.add(startUrl);
		
		//remove pending URL's and put them into urlString var as long as pending ArrayList isnt empty and finished ArrayList hasnt reached 100 urls
		while(!pendingURL.isEmpty() && finishedURL.size() <= 100){
			String urlString = pendingURL.remove(0);
		// as long as url hasnt been checked already continue adding finished urls to finished ArrayList
		if(!finishedURL.contains(urlString)){
			finishedURL.add(urlString);
			System.out.println("Crawled: " + urlString);
		//calls subURLs method to take Strings (url from pending Array) and find sub urls within those pages and adds them to pending Array 
		for(String s: getSubURLs(urlString)){
			if(!finishedURL.contains(s))
			pendingURL.add(s); 
		    }	
			}
		  }
		}
		
		//method to retrieve suburls to add to pendingArray
		public static ArrayList<String> getSubURLs(String urlString) {
			ArrayList<String> list = new ArrayList<>();
			
			try {
				URL url = new URL(urlString);
				Scanner input = new Scanner(url.openStream());
				int current = 0;
				while(input.hasNext()) {
					String line = input.nextLine();
					current = line.indexOf("http:", current);
					while(current > 0){
						int endIndex = line.indexOf("\"", current);
						if(endIndex > 0) { //Ensure that a corrent
						list.add(line.substring(current, endIndex));
						current = line.indexOf("http:", endIndex);
						}
						else 
							current = -1;
					}
				}
			}
			catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
				}
				return list;
		}
			
			
	
}

