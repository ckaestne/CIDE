package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EmptyElemTag extends GenASTNode {
  public EmptyElemTag(ASTStringNode element_id, ArrayList<Attribute> attribute, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("element_id", element_id),
      new PropertyZeroOrMore<Attribute>("attribute", attribute)
    }, firstToken, lastToken);
  }
  public EmptyElemTag(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EmptyElemTag(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getElement_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("element_id")).getValue();
  }
  public ArrayList<Attribute> getAttribute() {
    return ((PropertyZeroOrMore<Attribute>)getProperty("attribute")).getValue();
  }
}
