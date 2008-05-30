package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Cp2 extends Cp {
  public Cp2(ASTStringNode name, Modifier modifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("name", name),
      new PropertyZeroOrOne<Modifier>("modifier", modifier)
    }, firstToken, lastToken);
  }
  public Cp2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Cp2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getName() {
    return ((PropertyOne<ASTStringNode>)getProperty("name")).getValue();
  }
  public Modifier getModifier() {
    return ((PropertyZeroOrOne<Modifier>)getProperty("modifier")).getValue();
  }
}
