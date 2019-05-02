	import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class LogisticRegression {

	 //the learning step
    private double h;
    
    //the regularization parameter
    private double L;
     
    //the number of epochs
    private final int ITERATIONS = 1500;
     
    //the weights to be learned
    private double[] weights;
    public static File file=new File("D:\\STUDY\\SEM 4\\Machine learning\\Final_Project\\Final\\ML\\output.txt");
	public static PrintWriter out=null;
     
    public LogisticRegression(int n, double L){
        this.weights = new double[n];
        this.h = 0.001;
        this.L = L;
    }
    
    public LogisticRegression(int n, double h, double L){
        this.weights = new double[n];
        this.h = h;
        this.L = L;
    }
     
    public LogisticRegression(){}
     
    public double[] getWeights(){
        return weights;
    }
     
    public void setWeights(double[] weights){
        this.weights = weights;
    }
     
    public void setH(double h){
        this.h = h;
    }
    
    public void train(List<ReadFile.Instance> instances)
    {
        //Epochs
        for(int e = 0; e<= ITERATIONS; e++){
         /*   initialize the weights with random values between 0.0 and 1.0
           	for(int p=0; p<this.weights.length; p++){
              this.weights[p] = Math.random();
            }
            */
            
            //the log likelihood
            double llh = 0;
            
            for(int i=0; i<instances.size(); i++){
                double[] x = instances.get(i).x;
                double output = findProbability(x);   //the output of the sigmoid funcion
                int label = instances.get(i).label;   //the category that the training example belongs to
                double c;
                if(label==1){
                    c = 1;
                }else{
                    c = 0;
                }
                for(int j = 0; j<this.weights.length; j++){
                    if(j>=x.length) { break;}
                    else{this.weights[j] = (1-2*this.L*this.h)*this.weights[j] + this.h*(c - output)*x[j];
                    }}
                llh += getLogLikelihood(c,x);
            }
           // System.out.println("Epoch " + e + " weights " + Arrays.toString(this.weights) + " LogLikelihood " + llh);
        }
    }
     
     
    //Calculate the sum of w*x for each weight and attribute
    //call the sigmoid function with that s
    public double findProbability(double[] x){
        double s = 0;
        for(int i = 0; i<this.weights.length; i++){
            if(i>=x.length) { break;}
            else { s += this.weights[i]*x[i];        
        }}
        return sigmoid(s);
    }
     
    //Sigmoid with overflow check
    private double sigmoid(double s){
        if(s>20){
            s=20;
        }else if(s<-20){
            s=-20;
        }
        double exp = Math.exp(s);
        return exp/(1+exp);
    }
     
    //Calculate log likelihood on given data
    private double getLogLikelihood(double cat, double[] x) {
        return cat * Math.log(findProbability(x)) + (1-cat) * Math.log(1-findProbability(x));
    }

    public static void getFile(File file){
		try
	    {
			out= new PrintWriter(new FileOutputStream(file, true));
			
	    }
		catch(Exception e){
			 System.err.println("Server exception: " + e.toString());
	            e.printStackTrace();
		}
	}
		public static void main(String argv[]) {
			
		//-----------------Train case instances--------------//
			List<ReadFile.Instance> instances=new ArrayList<ReadFile.Instance>();
			//Test case instances
			List<ReadFile.Instance> testInstances=new ArrayList<ReadFile.Instance>();
			
			
			ReadFile read=new ReadFile();
			read.UI();
			
		    instances=read.returnList(); 
		    
			LogisticRegression logistic = new LogisticRegression(2,0.001);// assign learning rate and weights
			//logistic.printWeight();
			//data set with the label
			logistic.train(instances);
			//logistic.printWeight();
			
		//-------------------------	Test case start-----------//
			Random r = new Random();
			//read.TestUI();
			
			
			File f=new File("D:\\STUDY\\SEM 4\\Machine learning\\Final_Project\\Final\\ML\\GOOGL_Test.csv");
			ReadFile.readTestCSVFile(f,155);
			
			LogisticRegression.getFile(file);// file to write output
			testInstances=read.returnTestList();
			
			System.out.println("Weights are"+Arrays.toString(logistic.weights));
			
			//Print the probabilities that the training examples (first 367 of the  dataset) are classified in category M
		    System.out.println("\nThe probabilities that the training examples are classified in category M are :\n");
		    int error = 0;
		        
		    for(int i=0; i<testInstances.size(); i++)
		    {
		        int noise=r.nextInt(10);
		        System.out.println(i+"instance belongs in category " + testInstances.get(i).label + " " + logistic.findProbability(testInstances.get(i).x)/noise);
		        System.out.println(testInstances.get(i).label+","+logistic.findProbability(testInstances.get(i).x)/noise);
		        if(((logistic.findProbability(testInstances.get(i).x)>0.5) && ((testInstances.get(i).label)==0) ||(logistic.findProbability(testInstances.get(i).x)<0.5)&& 
		            		((testInstances.get(i).label)==1) )) 
		            	error++;
		     }
		     
		    System.out.println("\n The number of the wrong classified training examples is: "+error);

		    //test case data is 155 so if the test case vary you need to enter manual size 
		    float percentage=((float)error/155)*100;
		    System.out.println("\n The accuracy of model in percentage: "+(100-percentage));
			
			out.close();
		}

	}
