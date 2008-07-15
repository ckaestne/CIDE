package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_member_declaration extends GenASTNode {
  public class_member_declaration(attributes attributes, member_modifiers member_modifiers, class_member_declarationEnd class_member_declarationEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<attributes>("attributes", attributes),
      new PropertyZeroOrOne<member_modifiers>("member_modifiers", member_modifiers),
      new PropertyOne<class_member_declarationEnd>("class_member_declarationEnd", class_member_declarationEnd)
    }, firstToken, lastToken);
  }
  public class_member_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_member_declaration(cloneProperties(),firstToken,lastToken);
  }
  public attributes getAttributes() {
    return ((PropertyZeroOrOne<attributes>)getProperty("attributes")).getValue();
  }
  public member_modifiers getMember_modifiers() {
    return ((PropertyZeroOrOne<member_modifiers>)getProperty("member_modifiers")).getValue();
  }
  public class_member_declarationEnd getClass_member_declarationEnd() {
    return ((PropertyOne<class_member_declarationEnd>)getProperty("class_member_declarationEnd")).getValue();
  }
}
