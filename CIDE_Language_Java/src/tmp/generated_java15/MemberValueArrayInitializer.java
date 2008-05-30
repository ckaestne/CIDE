package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberValueArrayInitializer extends GenASTNode {
  public MemberValueArrayInitializer(MemberValue memberValue, ArrayList<MemberValue> memberValue1, ASTTextNode text455, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberValue>("memberValue", memberValue),
      new PropertyZeroOrMore<MemberValue>("memberValue1", memberValue1),
      new PropertyZeroOrOne<ASTTextNode>("text455", text455)
    }, firstToken, lastToken);
  }
  public MemberValueArrayInitializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new MemberValueArrayInitializer(cloneProperties(),firstToken,lastToken);
  }
  public MemberValue getMemberValue() {
    return ((PropertyOne<MemberValue>)getProperty("memberValue")).getValue();
  }
  public ArrayList<MemberValue> getMemberValue1() {
    return ((PropertyZeroOrMore<MemberValue>)getProperty("memberValue1")).getValue();
  }
  public ASTTextNode getText455() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text455")).getValue();
  }
}
