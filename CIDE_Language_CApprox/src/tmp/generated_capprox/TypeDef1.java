package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeDef1 extends TypeDef {
  public TypeDef1(ArrayList<AnyTypeDefToken> anyTypeDefToken, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<AnyTypeDefToken>("anyTypeDefToken", anyTypeDefToken)
    }, firstToken, lastToken);
  }
  public TypeDef1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeDef1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<AnyTypeDefToken> getAnyTypeDefToken() {
    return ((PropertyZeroOrMore<AnyTypeDefToken>)getProperty("anyTypeDefToken")).getValue();
  }
}
