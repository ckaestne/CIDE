package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_type_list extends GenASTNode {
  public interface_type_list(type_name type_name, ArrayList<type_name> type_name1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type_name>("type_name", type_name),
      new PropertyZeroOrMore<type_name>("type_name1", type_name1)
    }, firstToken, lastToken);
  }
  public interface_type_list(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_type_list(cloneProperties(),firstToken,lastToken);
  }
  public type_name getType_name() {
    return ((PropertyOne<type_name>)getProperty("type_name")).getValue();
  }
  public ArrayList<type_name> getType_name1() {
    return ((PropertyZeroOrMore<type_name>)getProperty("type_name1")).getValue();
  }
}
