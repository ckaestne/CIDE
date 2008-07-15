package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class compilation_unit extends GenASTNode implements ISourceFile {
  public compilation_unit(ArrayList<using_directive> using_directive, attributes_either attributes_either, compilation_unitEnd compilation_unitEnd, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<using_directive>("using_directive", using_directive),
      new PropertyZeroOrOne<attributes_either>("attributes_either", attributes_either),
      new PropertyZeroOrOne<compilation_unitEnd>("compilation_unitEnd", compilation_unitEnd),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public compilation_unit(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new compilation_unit(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<using_directive> getUsing_directive() {
    return ((PropertyZeroOrMore<using_directive>)getProperty("using_directive")).getValue();
  }
  public attributes_either getAttributes_either() {
    return ((PropertyZeroOrOne<attributes_either>)getProperty("attributes_either")).getValue();
  }
  public compilation_unitEnd getCompilation_unitEnd() {
    return ((PropertyZeroOrOne<compilation_unitEnd>)getProperty("compilation_unitEnd")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
