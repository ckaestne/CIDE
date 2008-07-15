package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclToken12 extends VarDeclToken {
  public VarDeclToken12(ASTStringNode findendcb, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findendcb", findendcb)
    }, firstToken, lastToken);
  }
  public VarDeclToken12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclToken12(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindendcb() {
    return ((PropertyOne<ASTStringNode>)getProperty("findendcb")).getValue();
  }
}
