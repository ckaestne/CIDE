package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclToken5 extends VarDeclToken {
  public VarDeclToken5(ASTStringNode other, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("other", other)
    }, firstToken, lastToken);
  }
  public VarDeclToken5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclToken5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getOther() {
    return ((PropertyOne<ASTStringNode>)getProperty("other")).getValue();
  }
}
