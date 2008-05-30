package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_people_list_Seq1 extends GenASTNode {
  public Content_people_list_Seq1(ArrayList<Element_person> element_person, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Element_person>("element_person", element_person)
    }, firstToken, lastToken);
  }
  public Content_people_list_Seq1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_people_list_Seq1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Element_person> getElement_person() {
    return ((PropertyZeroOrMore<Element_person>)getProperty("element_person")).getValue();
  }
}
