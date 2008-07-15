package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class switch_section extends GenASTNode {
  public switch_section(ArrayList<switch_label> switch_label, statement_list statement_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<switch_label>("switch_label", switch_label),
      new PropertyOne<statement_list>("statement_list", statement_list)
    }, firstToken, lastToken);
  }
  public switch_section(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new switch_section(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<switch_label> getSwitch_label() {
    return ((PropertyOneOrMore<switch_label>)getProperty("switch_label")).getValue();
  }
  public statement_list getStatement_list() {
    return ((PropertyOne<statement_list>)getProperty("statement_list")).getValue();
  }
}
