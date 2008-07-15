package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeDef2 extends TypeDef {
  public TypeDef2(ArrayList<AnyStmtToken> anyStmtToken, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<AnyStmtToken>("anyStmtToken", anyStmtToken)
    }, firstToken, lastToken);
  }
  public TypeDef2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeDef2(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<AnyStmtToken> getAnyStmtToken() {
    return ((PropertyZeroOrMore<AnyStmtToken>)getProperty("anyStmtToken")).getValue();
  }
}
