package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_modifiers extends GenASTNode {
  public type_modifiers(ArrayList<type_modifier> type_modifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<type_modifier>("type_modifier", type_modifier)
    }, firstToken, lastToken);
  }
  public type_modifiers(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_modifiers(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<type_modifier> getType_modifier() {
    return ((PropertyOneOrMore<type_modifier>)getProperty("type_modifier")).getValue();
  }
}
