package com.smartdatasolutions.test.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		// TODO
		return new MemberImporterImpl();
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {

		Set<String> ids = new HashSet<>();
		
	    List<Member> nonDuplicateMembers = new ArrayList<>();

	    for (Member m : membersFromFile) {
	        if (m != null && m.getId() != null) { 
	            if (!ids.contains(m.getId())) {
	                ids.add(m.getId());
	                nonDuplicateMembers.add(m);
	            }
	        }
	    }

	    return nonDuplicateMembers;
	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		 Map<String, List<Member>> membersByState = new HashMap<>();

	        for (Member m : validMembers) {
	            String st = m.getState();
	            List<Member> stateMembers = membersByState.getOrDefault(st, new ArrayList<>());
	            stateMembers.add(m);
	            membersByState.put(st, stateMembers);
	        }

	        return membersByState;
	}

	public static void main( String[] args ) {
		Main main = new Main();
		
        File inputFile = new File("Members.txt");
        
        String outputFilePath = "output/";
        
        String outputFileName = "outputFile.csv";

        try {
            File outputDir = new File(outputFilePath);
            
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            
            main.convert(inputFile, outputFilePath, outputFileName);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	}


