import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;  
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

public class ReadFile {
	 public static List<Instance> dataset = new ArrayList<Instance>();
	 public static List<Instance> TestDataset = new ArrayList<Instance>();
	 public static double[][] dataArray;//=new double[1876][6];
	 public static double[][] TestDataArray;
	 
	public static class Instance {
		public int label;
		public double[] x;

		public Instance(int label, double[] x) {
			this.label = label;
			this.x = x;
		}
	}
 public static List<Instance> readCSVFile(File file,int r){
	
	 try{
		 dataArray=new double[r][6];
	 int row=0,column=0;
	 FileReader filereader = new FileReader(file); 
	 CSVReader csvReader = new CSVReader(filereader); 
     String[] nextRecord; 
     nextRecord = csvReader.readNext();
     while ((nextRecord = csvReader.readNext()) != null) { 
    	 column=0;
         for (String cell : nextRecord) { 
        	 double tmp=DecimalFormat.getNumberInstance().parse(cell).doubleValue();
            // System.out.print(cell + "\t"+tmp); 
        	 dataArray[row][column++]=tmp;
         } 
         row++;    
     } 		
 } 
 catch (Exception e) { 
     e.printStackTrace(); 
	 System.out.println("No File Found");
 } 
	 return dataset;
 }
 
 public List<Instance> returnList()
 {
	 for(int i=0;i<dataArray.length;i++){
    	 for(int j=0;j<dataArray[0].length;j++){
    		 
    		 //System.out.print(dataArray[i][j]+"\t")
    	 }
    	 int th=(int) (dataArray[i][3]-dataArray[i][0]);
		 int label;
		 if(th<0)
			 label=0;
		 else
			 label=1;
    	 Instance instance = new Instance(label, dataArray[i]);
		 dataset.add(instance);
	 }
	 return dataset;
 }
 
 public List<Instance> returnTestList()
 {
	 for(int i=0;i<TestDataArray.length;i++){
    	 for(int j=0;j<TestDataArray[0].length;j++){
    		 
    		 //System.out.print(dataArray[i][j]+"\t")
    	 }
    	 int th=(int) (TestDataArray[i][3]-TestDataArray[i][0]);
		 int label;
		 if(th<0)
			 label=0;
		 else
			 label=1;
    	 Instance instance = new Instance(label, TestDataArray[i]);
    	 TestDataset.add(instance);
	 }
	 System.out.println("Test Data size "+TestDataset.size());
	 return TestDataset;
 }
 
 public static List<Instance> readTestCSVFile(File file,int r){
		
	 try{
		 TestDataArray=new double[r][6];
	 int row=0,column=0;
	 FileReader filereader = new FileReader(file); 
	 CSVReader csvReader = new CSVReader(filereader); 
     String[] nextRecord; 
     nextRecord = csvReader.readNext();
     while ((nextRecord = csvReader.readNext()) != null) { 
    	 column=0;
         for (String cell : nextRecord) { 
        	 double tmp=DecimalFormat.getNumberInstance().parse(cell).doubleValue();
            // System.out.print(cell + "\t"+tmp); 
        	 TestDataArray[row][column++]=tmp;
         } 
         row++;    
     } 		
 } 
 catch (Exception e) { 
     e.printStackTrace(); 
	 System.out.println("No File Found");
 } 
	 return dataset;
 }
/* public void TestUI(){
	  JFileChooser jFileChooser=new JFileChooser();
	   int result= jFileChooser.showOpenDialog(jFileChooser);
	    if(result==JFileChooser.APPROVE_OPTION)
	    {
			JOptionPane.showMessageDialog(jFileChooser, "You have selected "+jFileChooser.getSelectedFile()+"!");
			File file=jFileChooser.getSelectedFile();
			ReadFile.readCSVFile(file,126);
	    }
		
}*/
 
 public void UI(){
	  JFileChooser jFileChooser=new JFileChooser();
	 int result= jFileChooser.showOpenDialog(jFileChooser);
	    if(result==JFileChooser.APPROVE_OPTION)
	    {
			JOptionPane.showMessageDialog(jFileChooser, "You have selected "+jFileChooser.getSelectedFile()+"!");
			File file=jFileChooser.getSelectedFile();
			ReadFile.readCSVFile(file,1696);
	    }
 }
 
 }

