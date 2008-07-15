package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_member_declaration extends GenASTNode {
  public interface_member_declaration(attributes attributes, ArrayList<interface_member_modifier> interface_member_modifier, interface_member_declarationEnd interface_member_declarationEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<attributes>("attributes", attributes),
      new PropertyZeroOrMore<interface_member_modifier>("interface_member_modifier", interface_member_modifier),
      new PropertyOne<interface_member_declarationEnd>("interface_member_declarationEnd", interface_member_declarationEnd)
    }, firstToken, lastToken);
  }
  public interface_member_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_member_declaration(cloneProperties(),firstToken,lastToken);
  }
  public attributes getAttributes() {
    return ((PropertyZeroOrOne<attributes>)getProperty("attributes")).getValue();
  }
  public ArrayList<interface_member_modifier> getInterface_member_modifier() {
    return ((PropertyZeroOrMore<interface_member_modifier>)getProperty("interface_member_modifier")).getValue();
  }
  public interface_member_declarationEnd getInterface_member_declarationEnd() {
    return ((PropertyOne<interface_member_declarationEnd>)getProperty("interface_member_declarationEnd")).getValue();
  }
}
