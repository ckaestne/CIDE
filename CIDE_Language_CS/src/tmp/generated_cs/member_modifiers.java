package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class member_modifiers extends GenASTNode {
  public member_modifiers(ArrayList<member_modifier> member_modifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<member_modifier>("member_modifier", member_modifier)
    }, firstToken, lastToken);
  }
  public member_modifiers(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new member_modifiers(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<member_modifier> getMember_modifier() {
    return ((PropertyOneOrMore<member_modifier>)getProperty("member_modifier")).getValue();
  }
}
