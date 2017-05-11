package interviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;

public class SelectRequestIdsFromLogs {
	
	static class Record {
		public Record(String line, DateFormat format) throws ParseException {
			String[] tokens = line.split(", ");
			this.timeStamp = format.parse(tokens[0]);
			this.requestid = tokens[1];
			this.message = tokens[2];
			this.line = line;
		}
		Date timeStamp;
		String requestid;
		String message;
		String line;
	}
		
	public static LinkedList<String> main(String requestId, BufferedReader[] logs, DateFormat format, long requestTimeSpan) throws IOException, ParseException {
		
		LinkedList<LinkedList<Record>> parts = new LinkedList<LinkedList<Record>>();
		
		for(BufferedReader log : logs) {
			LinkedList<Record> filtered = getRequestIdLogs(requestId, log, format);
			if (filtered.isEmpty()) {
				continue;
			}
			parts.add(filtered);
		}
		
		LinkedList<Record> current = null;
		
		while (!parts.isEmpty()) {
			current = parts.remove();
			if (parts.isEmpty()) {
				break;
			}
			parts.add(merge(current, parts.remove()));
		}
		
		if (current == null) {
			return null;
		}
		
		LinkedList<String> result = new LinkedList<String>();
		
		for(Record record : current) {
			result.add(record.line);
		}
		
		return result;
	}
	
	private static LinkedList<Record> merge(LinkedList<Record> recordsA, LinkedList<Record> recordsB)
	{
		LinkedList<Record> merged = new LinkedList<Record>();
		
		int ixA = 0;
		int ixB = 0;
		
		int sizeA = recordsA.size();
		int sizeB = recordsB.size();
		while (ixA < sizeA || ixB < sizeB) {
			Record recordA = recordsA.get(ixA);
			Record recordB = recordsB.get(ixB);
			boolean moveA = ixA < sizeA 
					&& (recordA.timeStamp.before(recordB.timeStamp) || ixB >= sizeB);			
			if (moveA) {
				merged.add(recordA);
				ixA++;
			} else {
				merged.add(recordB);
				ixB++;
			}
		}
		
		return merged;		
	}
	
	private static LinkedList<Record> getRequestIdLogs(String requestId, BufferedReader log, DateFormat format) throws IOException, ParseException
	{
		String line = null;
		LinkedList<Record> lines = new LinkedList<Record>();
		while((line = log.readLine()) != null) {
			Record record = new Record(line, format);
			if (record.requestid == requestId) {
				lines.add(record);
			}
		}
		return lines;
	}
}