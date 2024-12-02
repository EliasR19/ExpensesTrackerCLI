package expenses;


public class ExpensesCLI {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if(args.length < 1) {
			throw new Exception("Must be an INPUT");
		}
		
			gestorGastos gestor = new gestorGastos();
			switch(args[0]) {
			case("add"):
				validarInput(args, 3, "add");
				gestor.addExpense(args[1], args[2]);
				break;
			case("update"):
				validarInput(args, 3, "update");
				gestor.updateExpense(args[1], args[2], args[3]);
				break;
			
			case("delete"):
				validarInput(args, 2, "delete");
				gestor.deleteExpense(args[1]);
				break;
			case("list"):
				validarInput(args, 1, "list");
				gestor.listExpense();
				break;
			case("summary"):
				validarInput(args, 1, "summary");
				if(args.length == 1) {
					gestor.summary();
				} else {
					validarMes(args[1]);
					gestor.summaryMonth(args[1]);
				}
			
			}
			
			gestor.saveExpense();
	}
	
	public static void validarInput(String[] input, int cantAtrib, String command) throws Exception {
		if(input.length <= cantAtrib - 1 || !input[0].equals(command)) {
			throw new Exception("Wrong Inpust: \n"
					+ "ADD: <add --description --amount>\n"
					+ "UPDATE: <update --id --description --amount>\n"
					+ "DELETE: <delete --id>\n"
					+ "VIEW-ALL: <list>\n"
					+ "SUMMARY-ALL: <summary>\n"
					+ "SUMMARY-MONTH: <summary --month>");
		}
	}
	
	public static void validarMes(String monthStr) throws Exception {
		int month = Integer.parseInt(monthStr);
		if(month < 1 || month > 12) {
			throw new Exception("Invalid Month");
		}
	}

	
}
