package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Attribute extends GenASTNode {
  public Attribute(ASTStringNode attr_name, ASTStringNode attr_val, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("attr_name", attr_name),
      new PropertyOne<ASTStringNode>("attr_val", attr_val)
    }, firstToken, lastToken);
  }
  public Attribute(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Attribute(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAttr_name() {
    return ((PropertyOne<ASTStringNode>)getProperty("attr_name")).getValue();
  }
  public ASTStringNode getAttr_val() {
    return ((PropertyOne<ASTStringNode>)getProperty("attr_val")).getValue();
  }
}
