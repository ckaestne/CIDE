package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyOpSymbol1 extends AnyOpSymbol {
  public AnyOpSymbol1(ASTStringNode symbols, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("symbols", symbols)
    }, firstToken, lastToken);
  }
  public AnyOpSymbol1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyOpSymbol1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSymbols() {
    return ((PropertyOne<ASTStringNode>)getProperty("symbols")).getValue();
  }
}
