package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class field_declarators extends GenASTNode {
  public field_declarators(field_declarator field_declarator, ArrayList<field_declarator> field_declarator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<field_declarator>("field_declarator", field_declarator),
      new PropertyZeroOrMore<field_declarator>("field_declarator1", field_declarator1)
    }, firstToken, lastToken);
  }
  public field_declarators(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new field_declarators(cloneProperties(),firstToken,lastToken);
  }
  public field_declarator getField_declarator() {
    return ((PropertyOne<field_declarator>)getProperty("field_declarator")).getValue();
  }
  public ArrayList<field_declarator> getField_declarator1() {
    return ((PropertyZeroOrMore<field_declarator>)getProperty("field_declarator1")).getValue();
  }
}
