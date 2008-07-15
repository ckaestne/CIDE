package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclToken13 extends VarDeclToken {
  public VarDeclToken13(Modifier modifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifier>("modifier", modifier)
    }, firstToken, lastToken);
  }
  public VarDeclToken13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclToken13(cloneProperties(),firstToken,lastToken);
  }
  public Modifier getModifier() {
    return ((PropertyOne<Modifier>)getProperty("modifier")).getValue();
  }
}
