
package RMI;

import java.rmi.registry.*;

public class rmi_b7_OPSyWfqH {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ObjectService service = (ObjectService) registry.lookup("RMIObjectService");

        String studentCode = "B23DCCN609";
        String qCode = "OPSyWfqH";

        TicketSla ticket = (TicketSla) service.requestObject(studentCode, qCode);

        String priority = ticket.getPriority();
        int hours = ticket.getOpenedHoursAgo();

        int limit = switch (priority) {
            case "CRITICAL" -> 2;
            case "HIGH" -> 8;
            case "MEDIUM" -> 24;
            case "LOW" -> 72;
            default -> Integer.MAX_VALUE;
        };

        boolean breached = hours > limit;
        ticket.setBreached(breached);

        if (!breached) {
            ticket.setAction("MONITOR");
        } else if (priority.equals("CRITICAL") || hours > 96) {
            ticket.setAction("ESCALATE_L2");
        } else {
            ticket.setAction("ESCALATE_L1");
        }

        service.submitObject(studentCode, qCode, ticket);
    }
}
