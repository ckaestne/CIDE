package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclToken11 extends VarDeclToken {
  public VarDeclToken11(Modifier modifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifier>("modifier", modifier)
    }, firstToken, lastToken);
  }
  public VarDeclToken11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclToken11(cloneProperties(),firstToken,lastToken);
  }
  public Modifier getModifier() {
    return ((PropertyOne<Modifier>)getProperty("modifier")).getValue();
  }
}
