package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclToken10 extends VarDeclToken {
  public VarDeclToken10(PPOtherIgnore pPOtherIgnore, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPOtherIgnore>("pPOtherIgnore", pPOtherIgnore)
    }, firstToken, lastToken);
  }
  public VarDeclToken10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclToken10(cloneProperties(),firstToken,lastToken);
  }
  public PPOtherIgnore getPPOtherIgnore() {
    return ((PropertyOne<PPOtherIgnore>)getProperty("pPOtherIgnore")).getValue();
  }
}
