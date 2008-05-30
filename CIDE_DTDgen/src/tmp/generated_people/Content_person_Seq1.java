package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_person_Seq1 extends GenASTNode {
  public Content_person_Seq1(Element_name element_name, Element_birthdate element_birthdate, Element_gender element_gender, Element_socialsecuritynumber element_socialsecuritynumber, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_name>("element_name", element_name),
      new PropertyZeroOrOne<Element_birthdate>("element_birthdate", element_birthdate),
      new PropertyZeroOrOne<Element_gender>("element_gender", element_gender),
      new PropertyZeroOrOne<Element_socialsecuritynumber>("element_socialsecuritynumber", element_socialsecuritynumber)
    }, firstToken, lastToken);
  }
  public Content_person_Seq1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_person_Seq1(cloneProperties(),firstToken,lastToken);
  }
  public Element_name getElement_name() {
    return ((PropertyOne<Element_name>)getProperty("element_name")).getValue();
  }
  public Element_birthdate getElement_birthdate() {
    return ((PropertyZeroOrOne<Element_birthdate>)getProperty("element_birthdate")).getValue();
  }
  public Element_gender getElement_gender() {
    return ((PropertyZeroOrOne<Element_gender>)getProperty("element_gender")).getValue();
  }
  public Element_socialsecuritynumber getElement_socialsecuritynumber() {
    return ((PropertyZeroOrOne<Element_socialsecuritynumber>)getProperty("element_socialsecuritynumber")).getValue();
  }
}
