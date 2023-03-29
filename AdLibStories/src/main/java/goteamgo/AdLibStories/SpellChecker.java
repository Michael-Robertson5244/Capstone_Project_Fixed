//package goteamgo.AdLibStories;
//
//public class SpellChecker {
//	// creating a new hashSet
//	
//		private HashSet<String> dictionary = new HashSet<String>();
//		
//		// creating two new vectors to hold the misspelled and suggested words
//		
//		private Vector <String> misspelled = new Vector<String>();
//		private Vector <String> suggested = new Vector<String>();
//		
//		/**
//		 * 
//		 * opens a file
//		 * 
//		 * @param path
//		 * @return
//		 * @throws FileNotFoundException
//		 * 
//		 */
//		
//		public String openFile(String path) throws FileNotFoundException {
//			
//			String incorrect = "";
//			
//			// read in file
//			
//			File file = new File(path);
//			Scanner scanner = new Scanner(file);
//			
//			String results = "";
//			
//			while(scanner.hasNextLine()) {
//				
//				// aggregates the next word into a string
//				
//				results += scanner.nextLine();
//				
//			}
//			
//			// splits the string into individual words for comparison
//			
//			String[] split = results.split(" |, |\\. |\\.|\\n|\\s");
//			
//			for(int index = 0; index < split.length; index++) {
//				
//				// if the dictionary contains the word continue
//				
//				if(dictionary.contains(split[index].toLowerCase())) {
//					
//					continue;
//					
//				}
//				
//				// if the dictionary does not contain the word add it to incorrect
//				
//				else {
//					
//					incorrect += split[index] + " ";
//					
//				}
//				
//			}
//		
//			// close the scanner
//			
//			scanner.close();
//			
//			return incorrect;
//			
//		}
//		
//		/**
//		 * 
//		 * reads the dictionary
//		 * 
//		 * @throws FileNotFoundException
//		 * 
//		 */
//
//		public void readDictionary() throws FileNotFoundException {
//			
//			System.out.println("LOADING DICTIONARY\n\n");
//			
//				// read "Words.txt" 
//			
//				File file = new File("Words.txt");
//				Scanner words = new Scanner(file);
//				
//				while(words.hasNextLine()) {
//					
//					String word = words.nextLine();
//					
//					// transforms words to lower case
//					
//					word = word.toLowerCase();
//					
//					// adds words to dictionary
//					
//					dictionary.add(word);
//				
//				}
//			
//				// closes the scanner
//				
//				words.close();
//				
//		}
//		
//		/**
//		 * 
//		 * opens the file to the text area
//		 * 
//		 * @param path
//		 * @return read
//		 * @throws FileNotFoundException
//		 * 
//		 */
//		
//		public String openFileToTextArea(String path) throws FileNotFoundException {
//			
//			// reading in the file
//			
//			File selectedFile = new File(path);
//			Scanner scanner = new Scanner(selectedFile);
//			
//			String read = "";
//			
//			while(scanner.hasNextLine()) {	
//				
//				read += scanner.nextLine();
//				
//			}
//			
//			System.out.println("\n\nOPENING FILE: " + path + "\n\n");
//			System.out.println('\t' + read + "\n\n");
//			
//			// closing the scanner
//			
//			scanner.close();
//			
//			return read;
//			
//		}
//		
//		/**
//		 * 
//		 * saves the text to the file
//		 * 
//		 * @param path
//		 * @param content
//		 * @throws FileNotFoundException
//		 * 
//		 */
//		
//		public void saveText(String path, String content) throws FileNotFoundException {
//			
//			// creates a printWriter for writing to a file
//			
//			PrintWriter writer = new PrintWriter(path);
//			writer.println(content);
//			
//			System.out.println("SAVING FILE TO PATH: \n\n");
//			System.out.println('\t' + path + "\n\n");
//			
//			System.out.println("THE FILE CONTENTS: \n\n");
//			System.out.println('\t' + content + "\n\n");
//			
//			// closing printWriter
//			
//			writer.close();
//			
//		}
//		
//		/**
//		 * 
//		 * adds a letter between each character and checks the dictionary
//		 * 
//		 * @param misspelledWord
//		 * @throws FileNotFoundException
//		 * 
//		 */
//		
//		public void addLetter(String misspelledWord) throws FileNotFoundException {
//			
//			for(char letter = 'a'; letter <= 'z'; letter++) {
//
//				String front = letter + misspelledWord;
//				String back =  misspelledWord + letter;
//
//				// if the misspelledWord doesn't have a space
//				
//				if(misspelledWord != " ") {
//
//					// if the word is in the dictionary
//					
//					if(dictionary.contains(front)) {
//
//						// add word to suggested
//						
//						suggested.add(front);
//
//					}
//
//					else if(dictionary.contains(back)) {
//
//						// add word to suggested
//						
//						suggested.add(back);
//
//					}
//					
//					else {
//						
//						String str = "";
//						
//						for(int index = 0; index < misspelledWord.length() - 1; index++) {
//							
//							str = misspelledWord.substring(0, index + 1) + letter + misspelledWord.substring(index + 1);
//							
//							if(dictionary.contains(str)) {
//							
//								suggested.add(str);
//								
//							}
//							
//						}
//						
//					}
//
//				}
//
//			}
//
//		}
//		
//		/**
//		 * 
//		 * removes a letter from a word and matches it to the dictionary
//		 * 
//		 * @param mispelledWord
//		 * @return suggested
//		 * @throws FileNotFoundException
//		 * 
//		 */
//		
//		public Vector<String> removeLetter(String mispelledWord) throws FileNotFoundException {
//			
//			for(int index = 0; index < mispelledWord.length(); index++) {
//				
//				// create a string buffer to delete characters at an index
//				
//				StringBuffer b = new StringBuffer(mispelledWord);
//				b.deleteCharAt(index);
//				
//				String buffered = new String(b);
//				
//				// if the word is in the dictionary
//				
//				if(dictionary.contains(buffered)) {
//					
//					// add word to suggested vector
//					
//					suggested.add(buffered);
//					
//				}
//				
//			}
//			
//			return suggested;
//			
//		}
//		
//		/**
//		 * 
//		 * swaps two character within a word
//		 * 
//		 * @param mispelledWord
//		 * @throws FileNotFoundException
//		 * 
//		 */
//		
//		public void swapLetter(String mispelledWord) throws FileNotFoundException {
//			
//			for(int index = 0; index < mispelledWord.length() - 1; index++) {
//				
//				char[] word = mispelledWord.toCharArray();
//				
//				char temp = word[index];
//				
//				// interchanging characters
//				
//				word[index] = word[index + 1];
//				word[index + 1] = temp;
//				
//				String swapped = new String(word);
//			
//				// if the word is in the dictionary 
//				
//				if(dictionary.contains(swapped)) {
//					
//					// add word to suggested vector 
//					
//					suggested.add(swapped);
//					
//				}
//				
//			}
//			
//		}
//		
//		/**
//		 * 
//		 * passes each word into the addLetter(), removeLetter(), and swapLetter()
//		 * functions and if they are a match for any, they are added into the suggested
//		 * vector. if there is no match, then add "There are no suggestions" to the vector.
//		 * 
//		 * @param misspelledWord
//		 * @throws FileNotFoundException
//		 * 
//		 */
//		
//		public void suggestions(String misspelledWord) throws FileNotFoundException {
//			
//			// if the dictionary does not contain the word
//			
//			if(dictionary.contains(misspelledWord) == false) {
//				
//				//calls the addLetter() function and passes the misspelled word into it
//				
//				addLetter(misspelledWord);
//				
//				// calls the removeLetter() function and passes the misspelled word into it
//				
//				removeLetter(misspelledWord);
//				
//				// calls the swapLetter() function and passes the misspelled word into it
//				
//				swapLetter(misspelledWord);
//				
//				// if the vector is empty 
//				
//				if(suggested.isEmpty()) {
//				
//					// add message to suggested vector
//					
//					suggested.add("There are no suggestions");
//					
//				}
//				
//			}
//			
//		}
//		
//		/**
//		 * 
//		 * gets the suggested words
//		 * 
//		 * @return suggested
//		 * 
//		 */
//		
//		public Vector<String> getSuggested() {
//			
//			return suggested;
//			
//		}
//}
