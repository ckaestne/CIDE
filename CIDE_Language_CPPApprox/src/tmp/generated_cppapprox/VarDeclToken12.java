package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclToken12 extends VarDeclToken {
  public VarDeclToken12(PPOtherIgnore pPOtherIgnore, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPOtherIgnore>("pPOtherIgnore", pPOtherIgnore)
    }, firstToken, lastToken);
  }
  public VarDeclToken12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclToken12(cloneProperties(),firstToken,lastToken);
  }
  public PPOtherIgnore getPPOtherIgnore() {
    return ((PropertyOne<PPOtherIgnore>)getProperty("pPOtherIgnore")).getValue();
  }
}
