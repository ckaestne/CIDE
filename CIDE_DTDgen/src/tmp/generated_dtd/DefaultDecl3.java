package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DefaultDecl3 extends DefaultDecl {
  public DefaultDecl3(ASTTextNode text17, AttribValue attribValue, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text17", text17),
      new PropertyOne<AttribValue>("attribValue", attribValue)
    }, firstToken, lastToken);
  }
  public DefaultDecl3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new DefaultDecl3(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText17() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text17")).getValue();
  }
  public AttribValue getAttribValue() {
    return ((PropertyOne<AttribValue>)getProperty("attribValue")).getValue();
  }
}
