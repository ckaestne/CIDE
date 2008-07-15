package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclToken4 extends VarDeclToken {
  public VarDeclToken4(ASTStringNode other, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("other", other)
    }, firstToken, lastToken);
  }
  public VarDeclToken4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclToken4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getOther() {
    return ((PropertyOne<ASTStringNode>)getProperty("other")).getValue();
  }
}
