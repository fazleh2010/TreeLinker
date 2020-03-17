import sys
import re

articles = {}

questions = []

class Question:

   def __init__(self, questions,content):
     self.myquestions= []
     self.content = content    
     self.myquestions = questions  
     
   def output(self):
     return_string = ""
     for question in self.myquestions:
       return_string = return_string + "Q: "+question +"\n"
       return_string = return_string + "A: "+content +"\n"
     return return_string

class Article:
  
  def __init__(self, number,topic):
    self.number = number
    self.topic = topic
    self.paragraphs = {}
    self.subparagraphs = {}
    self.annotations = []
	
  def addParagraph(self,number,content):
    self.paragraphs[number] = content
    
  def addSubParagraph(self,number,sub,content):
    self.subparagraphs[number][sub] = content
    
  def addAnnotation(self,annotation):
    self.annotations.append(annotation)

class Regulation:

  def __init__(self, issue, context="", modifier =""):
    self.issue = issue
    self.context = context
    self.modifier = modifier
    self.questions = []

  def verbalize(self):
    if self.context == "":
      self.questions.append("What regulations exist with respect to "+self.issue+"?")  
      self.questions.append("What rules govern the "+self.issue+"?") 
      self.questions.append("What are the rules of the "+self.issue+"?") 
      
       
    else:
      if self.modifier == "":
        self.questions.append("What regulations exist with respect to "+self.issue+" for "+self.context+"?")
        self.questions.append("What rules govern the "+self.issue+" in the context of "+self.context+"?")
      else:
        self.questions.append("What regulations exist with respect to "+self.issue+" for "+self.modifier+" "+context+"?")
    return self.questions  
  

class Requirement:

    def __init__(self, issue, context ="", modifier =""):
      self.issue = issue
      self.context = context
      self.modifier = modifier
      self.questions = []

    def verbalize(self):
      self.questions.append("What requirements does the "+self.issue +" have?")
      self.questions.append("What are requirements of the "+self.issue+" ?")
      if self.modifier == "":
	    self.questions.append("What requirements exist with respect to "+self.issue+" for "+self.context+"?")
      else:
	    self.questions.append("What requirements exist with respect to "+self.issue+" for "+self.modifier+" "+self.context+"?")
      return self.questions
	     
class Restriction:

    def __init__(self, issue, context ="", modifier =""):
      self.issue = issue
      self.context = context
      self.modifier = modifier
      self.questions = []

    def verbalize(self):
	  if self.modifier == "":
	    self.questions.append("What restrictions concerning "+self.issue+" apply for "+self.context+"?")
	  else:
	    self.questions.append("What restrictions concerning "+self.issue+" apply for "+self.modifier+" "+self.context+"?")	     
	  return self.questions
	 
class Obligation:

    def __init__(self, issue, context="", modifier =""):
      self.issue = issue
      self.context = context
      self.modifier = modifier
      self.questions = []

    def verbalize(self):
	  if self.modifier == "":
	    self.questions.append("What obligations exist with respect to "+self.issue+" for "+self.context+"?")
	    self.questions.append("What are obligations of the "+self.issue+" in the context of "+self.context+"?")
	    self.questions.append("What rights does a "+self.issue+" have in relation to "+self.context+"?")
	    
	  else:
	    self.questions("What obligations exist with respect to "+self.issue+" for "+self.modifier+" "+self.context+"?")
	  return self.questions


class Prohibition:

    def __init__(self, issue, context="", modifier =""):
      self.issue = issue
      self.context = context
      self.modifier = modifier
      self.questions = []

    def verbalize(self):
	  if self.modifier == "":
	    self.questions.append("What prohibitions exist with respect to "+self.issue+" for "+self.context+"?")
	  else:
	     self.questions.append("What prohibitions exist with respect to "+self.issue+" for "+self.modifier+" "+self.context+"?")
	  return self.questions   

class Right:

    def __init__(self, issue, context="", modifier =""):
      self.issue = issue
      self.context = context
      self.modifier = modifier
      self.questions = []

    def verbalize(self):	
	  if self.modifier == "":
	    self.questions.append("What rights does the "+self.issue+" hold in regards of the "+self.context+"?")
	    self.questions.append("What are the rights of the "+self.issue+" in relation to the "+self.context+"?")
	  else:
	    self.questions.append("What rights does the "+self.issue+" hold in regards of the "+self.modifier+" "+self.context+"?")
	  return self.questions

class Duty:

    def __init__(self, issue, context="", modifier =""):
      self.issue = issue
      self.context = context
      self.modifier = modifier
      self.questions = []

    def verbalize(self):
	  if self.modifier == "":
	    self.questions("What duties exist with respect to "+self.issue+" for "+self.context+"?")
	    self.questions("What are the duties of the "+self.issue+" for "+self.context+"?")
	  else:
	     self.questions("What duties exist with respect to "+self.issue+" for "+self.modifier+" "+self.context+"?")
	  return self.questions 
	     
class Purpose:

    def __init__(self, issue, context="", modifier =""):
      self.issue = issue
      self.context = context
      self.modifier = modifier
      self.questions = []

    def verbalize(self):
      if self.context == "":
        self.questions.append("What is the purpose of "+self.issue+"?")
      else:
        if self.modifier == "":
	      self.questions.append("What is the purpose of "+self.issue +" in the context of "+ self.context +"?")	  
        else:
          self.questions.append("What duties exist with respect to "+self.issue+" for "+self.modifier+" "+self.context+"?")
	  return self.questions
	   
class Definition:

    def __init__(self, issue, context="", modifier =""):
      self.issue = issue
      self.context = context
      self.modifier = modifier
      self.questions = []

    def verbalize(self):
      if self.context == "":
        self.questions.append("What is a "+self.issue+"?")
        self.questions.append("How is a "+self.issue+" defined?")
        self.questions.append("What is understood by a "+ self.issue+"?")
        self.questions.append("What is meant by a "+ self.issue+"?")
      return self.questions


f= open(sys.argv[1],"r")

for line in f:
        
  line = line.rstrip('\r\n')      
  values = line.split("\t")
  if len(values) >= 5: 
    
    #print(line)
    
    article_no = values[0]
    topic = values[1]
    paragraph = values[2]
    subparagraph = values[3]
    content = values[4]
    predicates = values[5].split(",")
    
    for predicate in predicates:
     
      if paragraph in articles:
    	article = articles[article_no]
      else:
		article = Article(article_no,topic)
		articles[article_no] = article
	
      if subparagraph == "":
		article.addParagraph(paragraph,content)	
		
      if predicate == "regulations":
        if len(values)==8:
	      regulation = Regulation(values[6],values[7])
        else:
	   	  regulation = Regulation(values[6])
        questions.append(Question(regulation.verbalize(), paragraph + " " + subparagraph + " " + content))
        article.addAnnotation(regulation)
	
      if predicate == "requirement":
        if len(values)==8:
	      requirement = Requirement(values[6],values[7])
        else:
	   	  requirement = Requirement(values[6])
        questions.append(Question(regulation.verbalize(), paragraph + " " + subparagraph + " " + content))
        article.addAnnotation(requirement)
	
      if predicate == "prohibition":
        if len(values)==8:
           prohibition = Prohibition(values[6],values[7])
        else:
           prohibition = Prohibition(values[6])
        questions.append(Question(regulation.verbalize(), paragraph + " " + subparagraph + " " + content))
        article.addAnnotation(prohibition)
	
      if predicate == "obligation":
        if len(values)==8:
          obligation = Olbigation(values[6],values[7])
        else:
          obligation = Obligation(values[6])
        questions.append(Question(regulation.verbalize(), paragraph + " " + subparagraph + " " + content))
        article.addAnnotation(obligation)
	
      if predicate == "purpose_goal":
        if len(values)==8:
           purpose = Purpose(values[6],values[7])
        else:
           purpose = Purpose(values[6])
        questions.append(Question(regulation.verbalize(), paragraph + " " + subparagraph + " " + content))
        article.addAnnotation(purpose)
	
      if predicate == "right":
        if len(values)==8:
          right = Right(values[6],values[7])
        else:
          right = Right(values[6])
        questions.append(Question(regulation.verbalize(), paragraph + " " + subparagraph + " " + content))
        article.addAnnotation(right)
	
      if predicate == "duty":
        if len(values)==8:
          duty = Duty(values[6],values[7])
        else:
          duty = Duty(values[6])
        questions.append(Question(regulation.verbalize(), paragraph + " " + subparagraph + " " + content))
        article.addAnnotation(duty)
        
      if predicate == "definition":
        if len(values)==8:
          definition = Definition(values[6],values[7])
        else:
          definition = Definition(values[6])
        questions.append(Question(definition.verbalize(), paragraph + " " + subparagraph + " " + content))
        article.addAnnotation(definition)
		
f.close()
		
f= open("questions.txt","w")		
		
for question in questions:
  f.write(question.output())
f.close()
    

