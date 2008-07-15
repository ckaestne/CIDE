package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclToken6 extends VarDeclToken {
  public VarDeclToken6(ASTStringNode symbols, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("symbols", symbols)
    }, firstToken, lastToken);
  }
  public VarDeclToken6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclToken6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSymbols() {
    return ((PropertyOne<ASTStringNode>)getProperty("symbols")).getValue();
  }
}
