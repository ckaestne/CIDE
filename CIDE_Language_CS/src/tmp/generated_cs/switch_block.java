package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class switch_block extends GenASTNode {
  public switch_block(ArrayList<switch_section> switch_section, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<switch_section>("switch_section", switch_section)
    }, firstToken, lastToken);
  }
  public switch_block(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new switch_block(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<switch_section> getSwitch_section() {
    return ((PropertyOneOrMore<switch_section>)getProperty("switch_section")).getValue();
  }
}
