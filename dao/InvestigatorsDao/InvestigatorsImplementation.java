package InvestigatorsDao;

import java.util.List;

public class InvestigatorsImplementation {
	public static void main(String[] args) {
		InvestigatorsDaoClass InvDao = new InvestigatorsDaoClass();
		List<Object> data = InvDao.getData("Production", "productionNo");
		List<Object> result = InvDao.checkData(data, "MissData", "FieldIncoherent", "([0-9A-Z]{1}?)([0-9]{3}?)([0-9]{3}?)([0-9]{3}?)");
		System.out.println("4535435435");
	}

}
