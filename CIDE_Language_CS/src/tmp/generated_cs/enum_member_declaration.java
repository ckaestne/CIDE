package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class enum_member_declaration extends GenASTNode {
  public enum_member_declaration(attributes attributes, identifier identifier, enum_member_assignment enum_member_assignment, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<attributes>("attributes", attributes),
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrOne<enum_member_assignment>("enum_member_assignment", enum_member_assignment)
    }, firstToken, lastToken);
  }
  public enum_member_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new enum_member_declaration(cloneProperties(),firstToken,lastToken);
  }
  public attributes getAttributes() {
    return ((PropertyZeroOrOne<attributes>)getProperty("attributes")).getValue();
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public enum_member_assignment getEnum_member_assignment() {
    return ((PropertyZeroOrOne<enum_member_assignment>)getProperty("enum_member_assignment")).getValue();
  }
}
