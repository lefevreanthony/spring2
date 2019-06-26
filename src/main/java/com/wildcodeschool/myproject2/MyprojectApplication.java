package com.wildcodeschool.myproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@SpringBootApplication
public class MyprojectApplication {

    public static String[][] doctors = {
		{"Christopher Eccleston", "9", "41", "13"},
		{"David Tennant", "10", "34", "47"},
		{"Matt Smith", "11", "27", "44"},
		{"Peter Capaldi", "12", "55", "40"},
		{"Jodie Whittaker", "13", "35", "11"}
    };


    public static void main(String[] args) {
        SpringApplication.run(MyprojectApplication.class, args);
    }

    @RequestMapping("/doctor/{number}")
    @ResponseBody
    public Doctor getDoctor(@PathVariable int number, @RequestParam(value = "details", required=false) boolean details) {
        if (number >= 9 && number <= 13) {
			if (details) {
				return new ExtendedDoctor(doctors[number-9][0], doctors[number-9][1], doctors[number-9][2], doctors[number-9][3]);
			}
			return new Doctor(doctors[number-9][0], doctors[number-9][1]);
		}
		else if (number >= 1 && number < 9) {
			throw new ResponseStatusException(HttpStatus.SEE_OTHER);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible de récupérer l'incarnation " + number);
        }
            
        
    }


    class ExtendedDoctor extends Doctor {

        private String agesAtStart;
        private String numberOfEpisode;
    
        public ExtendedDoctor(String name, String number, String agesAtStart, String numberOfEpisode) {
            super(name, number);
            this.agesAtStart = agesAtStart;
            this.numberOfEpisode = numberOfEpisode;
        }

        public String getAgesAtStart() {
            return agesAtStart;
        }

        public void setAgesAtStart(String agesAtStart) {
            this.agesAtStart = agesAtStart;
        }

        public String getNumberOfEpisode() {
            return numberOfEpisode;
        }

        public void setNumberOfEpisode(String numberOfEpisode) {
            this.numberOfEpisode = numberOfEpisode;
        }
    
        
    }
    class Doctor {

        private String name;
        private String number;

        public Doctor(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
