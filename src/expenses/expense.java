package expenses;

import java.time.LocalDate;

public class expense {
	
	static int idSiguiente = 1;
	int id;
	String description;
	int amount;
	LocalDate date;
	
	public expense() {
		id = idSiguiente;
		idSiguiente++;
		date = LocalDate.now();
		amount = 0;
	}
	
	
	public static expense fromCSV(String gasto) {

		String[] exp1 = gasto.split(",");

		
		String description = exp1[1].strip();
		String amount = exp1[2].strip();
		String date = exp1[3].strip();
	
//		System.out.println("d" + description);
//		System.out.println("a" + amount);
		
		expense newExpense = new expense();
		newExpense.description = description;
		newExpense.amount = Integer.parseInt(amount);
		newExpense.date = LocalDate.parse(date);
		
		return newExpense;
	}
	
	public String toCSV() {
		return id + "," + description + "," + amount + "," + date + "\n";
	}
	
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public int getAmount() {
		return amount;
	}
	public LocalDate getDate() {
		return date;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
