package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclTokenOrComma1 extends VarDeclTokenOrComma {
  public VarDeclTokenOrComma1(VarDeclToken varDeclToken, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<VarDeclToken>("varDeclToken", varDeclToken)
    }, firstToken, lastToken);
  }
  public VarDeclTokenOrComma1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclTokenOrComma1(cloneProperties(),firstToken,lastToken);
  }
  public VarDeclToken getVarDeclToken() {
    return ((PropertyOne<VarDeclToken>)getProperty("varDeclToken")).getValue();
  }
}
