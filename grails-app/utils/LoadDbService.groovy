

class LoadDbService {
	
	boolean transactional = true
	def rootDir = 'something'
	
	// a set method for property rootDir
	public void setRootDir(String dir) {

		rootDir = dir
		if (! (dir.endsWith("/") || dir.endsWith("\\")))
			rootDir += File.separator
	}	
	
//	def load() { 				
//		 
//		def csv
//
//        csv = new CsvFileReader(rootDir +  'Epic.csv')
//
//        csv.forEachRow () {
//            new Epic(it).save(flush:true)
//        }
//
//        csv = new CsvFileReader(rootDir +  'UserStory.csv')
//
//        csv.forEachRow () {
//            new UserStory(it).save(flush:true)
//        }
//
//        csv = new CsvFileReader(rootDir +  'Account.csv')
//
////        csv.forEachRow () {
////            println it
////            def b = it['bank'] = Bank.findByName(it['bank'])
////            def e = it['entity'] = Entity.findByName(it['entity'])
////
////            println it
////
////            def a = new Account(it)
////
////            b.addToAccounts(a)
////            b.save(flush:true)
////
////            e.addToAccounts(a)
////            e.save(flush:true)
//        }
//	}
}
