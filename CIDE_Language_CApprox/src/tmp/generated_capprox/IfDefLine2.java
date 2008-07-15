package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IfDefLine2 extends IfDefLine {
  public IfDefLine2(ASTStringNode identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public IfDefLine2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IfDefLine2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier1() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier1")).getValue();
  }
}
