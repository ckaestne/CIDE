package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class field_declaration extends GenASTNode {
  public field_declaration(field_declaration_start field_declaration_start, field_declarators field_declarators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<field_declaration_start>("field_declaration_start", field_declaration_start),
      new PropertyZeroOrOne<field_declarators>("field_declarators", field_declarators)
    }, firstToken, lastToken);
  }
  public field_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new field_declaration(cloneProperties(),firstToken,lastToken);
  }
  public field_declaration_start getField_declaration_start() {
    return ((PropertyOne<field_declaration_start>)getProperty("field_declaration_start")).getValue();
  }
  public field_declarators getField_declarators() {
    return ((PropertyZeroOrOne<field_declarators>)getProperty("field_declarators")).getValue();
  }
}
