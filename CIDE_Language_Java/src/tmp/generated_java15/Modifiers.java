package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Modifiers extends GenASTNode {
  public Modifiers(ArrayList<Modifier> modifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Modifier>("modifier", modifier)
    }, firstToken, lastToken);
  }
  public Modifiers(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Modifiers(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Modifier> getModifier() {
    return ((PropertyZeroOrMore<Modifier>)getProperty("modifier")).getValue();
  }
}
