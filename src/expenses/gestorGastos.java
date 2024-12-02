package expenses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.*;
import java.util.stream.Stream;

public class gestorGastos {
	static List<expense> gastos = new ArrayList<expense>();
	Path path = Path.of("expenses.csv");
	
	public gestorGastos() {
		gastos = loadGastos();
	}
	
	
	public void addExpense(String desc, String amount) {
		expense newExp = new expense();
		newExp.setDescription(desc);
		newExp.setAmount(Integer.parseInt(amount));
		
		gastos.add(newExp);
		System.out.println("Add successfully ID " + newExp.getId());
	}
	
	public void updateExpense(String id, String desc, String amount) {
		expense currExp = this.findExpense(id);
		currExp.setDescription(desc);
		currExp.setAmount(Integer.parseInt(amount));
		
		System.out.println("Update successfully ID " + currExp.getId());
	}
	
	public void deleteExpense(String idStr) {
		gastos.remove(this.findExpense(idStr));
		
		System.out.println("Remove successfully ID " + idStr);
	}
	
	public void listExpense() {
		for(expense e : gastos) {
			System.out.println("ID: " + e.getId() +" |Description: " + e.getDescription() + " |Amount: " + e.getAmount() + " |Date: " + e.getDate());
		}
	}
	
	public void summary() {
		System.out.println("Summary: " + this.summary(gastos));
	}
	
	public void summaryMonth(String monthInt) {
		System.out.println("Summary of month " + monthInt + "(" + Month.of(Integer.parseInt(monthInt)) +"): " + this.summary(this.expenseMonth(monthInt)));
	}
	
	
	
	private List<expense> expenseMonth(String monthInt) {
		int month = Integer.parseInt(monthInt);
		List<expense> expenseMonth = gastos.stream().filter(e -> e.getDate().getMonth().getValue() == month).toList();
		return expenseMonth;
	}


	private expense findExpense(String idStr) {
		int id = Integer.parseInt(idStr);
		expense lookedExp = gastos.stream().filter( e -> e.getId() == id).findAny().orElseThrow();
		return lookedExp;
	}
	private int summary(List<expense> gastos) {
		return gastos.stream().mapToInt(e -> e.getAmount()).sum();
	}

	public List<expense> loadGastos() {
		List<expense> loadGastos = new ArrayList<>();
		
		//Si no existe el archivo lo crea y devuelve una lista vacia
		if(!Files.exists(path)) {			
			try {
				System.out.println("Creando archivo.");
				path = Files.createFile(path);
				return new ArrayList<>();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			//Si existe carga los datos del archivo en la lista
			try {
				String content = Files.readString(path);
				
				String[] expensesLoad = content.replace("ID,Description,Amount,Date", "").replaceFirst("\n", "").split("\n");

				for(String e : expensesLoad) {
					loadGastos.add(expense.fromCSV(e));

				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return loadGastos;	
		
	}
	
	public void saveExpense() {
		StringBuilder str = new StringBuilder();
		str.append("ID,Description,Amount,Date\n");
		
		for(expense e : gastos) {
			str.append(e.toCSV());
		}
		
		try {
			Files.writeString(path, str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void print(List<expense> list) {
		for(expense e : list) {
			System.out.println("ID: " + e.getId() +" |Description: " + e.getDescription() + " |Amount: " + e.getAmount() + " |Date: " + e.getDate());
		}
	}

}
