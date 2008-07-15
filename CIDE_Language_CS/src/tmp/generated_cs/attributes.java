package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attributes extends GenASTNode {
  public attributes(ArrayList<local_attribute> local_attribute, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<local_attribute>("local_attribute", local_attribute)
    }, firstToken, lastToken);
  }
  public attributes(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attributes(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<local_attribute> getLocal_attribute() {
    return ((PropertyOneOrMore<local_attribute>)getProperty("local_attribute")).getValue();
  }
}
