package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclTokenOrComma2 extends VarDeclTokenOrComma {
  public VarDeclTokenOrComma2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public VarDeclTokenOrComma2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclTokenOrComma2(cloneProperties(),firstToken,lastToken);
  }
}
