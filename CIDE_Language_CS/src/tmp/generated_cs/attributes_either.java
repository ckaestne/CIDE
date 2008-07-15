package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attributes_either extends GenASTNode {
  public attributes_either(ArrayList<attribute_section_start> attribute_section_start, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<attribute_section_start>("attribute_section_start", attribute_section_start)
    }, firstToken, lastToken);
  }
  public attributes_either(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attributes_either(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<attribute_section_start> getAttribute_section_start() {
    return ((PropertyOneOrMore<attribute_section_start>)getProperty("attribute_section_start")).getValue();
  }
}
