package com.tss.TSSEurekaClient;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.Environment;

@SpringBootTest
@AutoConfigureMockMvc  
class TssEurekaClientApplicationTests {

    
        @Autowired
	private ReportController reportController;
        
        @Autowired
	private DocumentCreator documentCreator;    
        
        @Autowired
        private ServerProperties serverProperties;   
        
        @Autowired
        private Environment env;        
        
        
	@Test
	void contextLoads() {
             assertThat(reportController).isNotNull();
	}
	@Autowired
	private MockMvc mockMvc;    
        
        @Test   
        public void testFile() {
            //java.io.File fileTest = documentCreator.createPDF("D://Report.pdf");
            //java.io.File fileTest = new java.io.File("D://Report.pdf");
            //boolean fileExists = fileTest.exists();
            //assertThat(fileExists).isTrue();            
            java.io.InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Report.pdf");
            Assertions.assertNotNull(inputStream, "Plik Report.pdf powinien być dostępny w zasobach testowych");

//            
//            java.io.File fileTest1 = documentCreator.createPDF();
//            java.io.File file1 = new java.io.File("D://Report.pdf");
//            boolean fileExists1 = fileTest1.exists();
//            assertThat(fileExists1).isTrue();            
            
        }    
        
        @Test   
        public void testServer() {  
            int EXPECTED_PORT = 8083;
            int port = serverProperties.getPort();
            Assertions.assertEquals(EXPECTED_PORT, port);
            

//            String EXPECTED_ADDRESS = "localhost";
//            String hostnameIP = env.getProperty("server.address");
//            Assertions.assertEquals(EXPECTED_ADDRESS, hostnameIP);              
            
//            String EXPECTED_ADDRESS = "localhost";
//            String hostAddress = serverProperties.getAddress().getHostAddress();
//            Assertions.assertEquals(EXPECTED_ADDRESS, hostAddress);            
            
//            assertEquals(EXPECTED_PORT, port);
        }      
        
//	@Test
//	void mainViewTest() throws Exception {
//
//            this.mockMvc.perform(get("/reports/userreportpdf"))
//                    .andDo(print())
//                    .andExpect(status().isOk());
//        }          

}
