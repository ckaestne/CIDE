package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DTD extends GenASTNode implements ISourceFile {
  public DTD(ArrayList<DTDEntry> dTDEntry, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<DTDEntry>("dTDEntry", dTDEntry),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public DTD(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new DTD(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<DTDEntry> getDTDEntry() {
    return ((PropertyZeroOrMore<DTDEntry>)getProperty("dTDEntry")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
