package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AttribValue extends GenASTNode {
  public AttribValue(ASTStringNode quotedstr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("quotedstr", quotedstr)
    }, firstToken, lastToken);
  }
  public AttribValue(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new AttribValue(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getQuotedstr() {
    return ((PropertyOne<ASTStringNode>)getProperty("quotedstr")).getValue();
  }
}
